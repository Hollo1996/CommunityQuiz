package hu.bme.communityquiz.network.auth

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.apache.oltu.oauth2.client.OAuthClient
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest
import org.apache.oltu.oauth2.client.request.OAuthClientRequest
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder
import org.apache.oltu.oauth2.common.exception.OAuthProblemException
import org.apache.oltu.oauth2.common.exception.OAuthSystemException
import org.apache.oltu.oauth2.common.message.types.GrantType
import org.apache.oltu.oauth2.common.token.BasicOAuthToken
import java.io.IOException
import java.net.HttpURLConnection

class OAuth(client: OkHttpClient, requestBuilder: TokenRequestBuilder) : Interceptor {
    interface AccessTokenListener {
        fun notify(token: BasicOAuthToken?)
    }

    @get:Synchronized
    @set:Synchronized
    @Volatile
    var accessToken: String? = null
    private val oauthClient: OAuthClient
    var tokenRequestBuilder: TokenRequestBuilder
    var authenticationRequestBuilder: AuthenticationRequestBuilder? = null
    private var accessTokenListener: AccessTokenListener? = null

    constructor(requestBuilder: TokenRequestBuilder) : this(OkHttpClient(), requestBuilder) {}
    constructor(
        flow: OAuthFlow?,
        authorizationUrl: String?,
        tokenUrl: String?,
        scopes: String?
    ) : this(OAuthClientRequest.tokenLocation(tokenUrl).setScope(scopes)) {
        setFlow(flow)
        authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl)
    }

    fun setFlow(flow: OAuthFlow?) {
        when (flow) {
            OAuthFlow.accessCode, OAuthFlow.implicit -> tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE)
            OAuthFlow.password -> tokenRequestBuilder.setGrantType(GrantType.PASSWORD)
            OAuthFlow.application -> tokenRequestBuilder.setGrantType(GrantType.CLIENT_CREDENTIALS)
            else -> {
            }
        }
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        // If the request already have an authorization (eg. Basic auth), do nothing
        if (request.header("Authorization") != null) {
            return chain.proceed(request)
        }

        // If first time, get the token
        val oAuthRequest: OAuthClientRequest
        if (accessToken == null) {
            updateAccessToken(null)
        }

        // Build the request
        val rb = request.newBuilder()
        val requestAccessToken: String = String(accessToken)
        oAuthRequest = try {
            OAuthBearerClientRequest(request.url().toString())
                .setAccessToken(requestAccessToken)
                .buildHeaderMessage()
        } catch (e: OAuthSystemException) {
            throw IOException(e)
        }
        for ((key, value) in oAuthRequest.headers) {
            rb.addHeader(key, value)
        }
        rb.url(oAuthRequest.locationUri)

        //Execute the request
        val response = chain.proceed(rb.build())

        // 401 most likely indicates that access token has expired.
        // Time to refresh and resend the request
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            updateAccessToken(requestAccessToken)
            return intercept(chain)
        }
        return response
    }

    @Synchronized
    @Throws(IOException::class)
    fun updateAccessToken(requestAccessToken: String?) {
        if (accessToken == null || accessToken == requestAccessToken) {
            try {
                val accessTokenResponse =
                    oauthClient.accessToken(tokenRequestBuilder.buildBodyMessage())
                accessToken = accessTokenResponse.accessToken
                if (accessTokenListener != null) {
                    accessTokenListener!!.notify(accessTokenResponse.oAuthToken as BasicOAuthToken)
                }
            } catch (e: OAuthSystemException) {
                throw IOException(e)
            } catch (e: OAuthProblemException) {
                throw IOException(e)
            }
        }
    }

    fun registerAccessTokenListener(accessTokenListener: AccessTokenListener?) {
        this.accessTokenListener = accessTokenListener
    }

    init {
        oauthClient = OAuthClient(OAuthOkHttpClient(client))
        tokenRequestBuilder = requestBuilder
    }
}