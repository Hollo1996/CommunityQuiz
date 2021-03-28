package hu.bme.communityquiz.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import hu.bme.communityquiz.network.auth.ApiKeyAuth
import hu.bme.communityquiz.network.auth.HttpBasicAuth
import hu.bme.communityquiz.network.auth.OAuth
import hu.bme.communityquiz.network.auth.OAuth.AccessTokenListener
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.AuthenticationRequestBuilder
import org.apache.oltu.oauth2.client.request.OAuthClientRequest.TokenRequestBuilder
import retrofit2.Converter
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type
import java.util.*

class ApiClient() {
    private var apiAuthorizations: MutableMap<String, Interceptor>
    var okClient: OkHttpClient? = null
        private set
    var adapterBuilder: Retrofit.Builder? = null

    constructor(authNames: Array<String>) : this() {
        for (authName in authNames) {
            throw RuntimeException("auth name \"$authName\" not found in available auth names")
        }
    }

    /**
     * Basic constructor for single auth name
     * @param authName
     */
    constructor(authName: String) : this(arrayOf<String>(authName)) {}

    /**
     * Helper constructor for single api key
     * @param authName
     * @param apiKey
     */
    constructor(authName: String, apiKey: String) : this(authName) {
        setApiKey(apiKey)
    }

    /**
     * Helper constructor for single basic auth or password oauth2
     * @param authName
     * @param username
     * @param password
     */
    constructor(authName: String, username: String, password: String) : this(authName) {
        setCredentials(username, password)
    }

    /**
     * Helper constructor for single password oauth2
     * @param authName
     * @param clientId
     * @param secret
     * @param username
     * @param password
     */
    constructor(
        authName: String,
        clientId: String?,
        secret: String?,
        username: String?,
        password: String?
    ) : this(authName) {
        tokenEndPoint
            ?.setClientId(clientId)
            ?.setClientSecret(secret)
            ?.setUsername(username)
            ?.setPassword(password)
    }

    fun createDefaultAdapter() {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create()
        okClient = OkHttpClient()
        var baseUrl = "https://localhost/communityQuiz"
        if (!baseUrl.endsWith("/")) baseUrl = "$baseUrl/"
        adapterBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okClient)
            .addConverterFactory(GsonCustomConverterFactory.create(gson))
    }

    fun <S> createService(serviceClass: Class<S>?): S {
        return adapterBuilder!!.build().create(serviceClass)
    }

    /**
     * Helper method to configure the first api key found
     * @param apiKey
     */
    private fun setApiKey(apiKey: String) {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is ApiKeyAuth) {
                apiAuthorization.apiKey = apiKey
                return
            }
        }
    }

    /**
     * Helper method to configure the username/password for basic auth or password oauth
     * @param username
     * @param password
     */
    private fun setCredentials(username: String, password: String) {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is HttpBasicAuth) {
                apiAuthorization.setCredentials(username, password)
                return
            }
            if (apiAuthorization is OAuth) {
                apiAuthorization.tokenRequestBuilder.setUsername(username).setPassword(password)
                return
            }
        }
    }

    /**
     * Helper method to configure the token endpoint of the first oauth found in the apiAuthorizations (there should be only one)
     * @return
     */
    val tokenEndPoint: TokenRequestBuilder?
        get() {
            for (apiAuthorization in apiAuthorizations.values) {
                if (apiAuthorization is OAuth) {
                    return apiAuthorization.tokenRequestBuilder
                }
            }
            return null
        }

    /**
     * Helper method to configure authorization endpoint of the first oauth found in the apiAuthorizations (there should be only one)
     * @return
     */
    val authorizationEndPoint: AuthenticationRequestBuilder?
        get() {
            for (apiAuthorization in apiAuthorizations.values) {
                if (apiAuthorization is OAuth) {
                    return apiAuthorization.authenticationRequestBuilder
                }
            }
            return null
        }

    /**
     * Helper method to pre-set the oauth access token of the first oauth found in the apiAuthorizations (there should be only one)
     * @param accessToken
     */
    fun setAccessToken(accessToken: String?) {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is OAuth) {
                apiAuthorization.accessToken = accessToken
                return
            }
        }
    }

    /**
     * Helper method to configure the oauth accessCode/implicit flow parameters
     * @param clientId
     * @param clientSecret
     * @param redirectURI
     */
    fun configureAuthorizationFlow(clientId: String?, clientSecret: String?, redirectURI: String?) {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is OAuth) {
                val oauth = apiAuthorization
                oauth.tokenRequestBuilder
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setRedirectURI(redirectURI)
                oauth.authenticationRequestBuilder
                    ?.setClientId(clientId)
                    ?.setRedirectURI(redirectURI)
                return
            }
        }
    }

    /**
     * Configures a listener which is notified when a new access token is received.
     * @param accessTokenListener
     */
    fun registerAccessTokenListener(accessTokenListener: AccessTokenListener?) {
        for (apiAuthorization in apiAuthorizations.values) {
            if (apiAuthorization is OAuth) {
                apiAuthorization.registerAccessTokenListener(accessTokenListener)
                return
            }
        }
    }

    /**
     * Adds an authorization to be used by the client
     * @param authName
     * @param authorization
     */
    fun addAuthorization(authName: String, authorization: Interceptor) {
        if (apiAuthorizations.containsKey(authName)) {
            throw RuntimeException("auth name \"$authName\" already in api authorizations")
        }
        apiAuthorizations[authName] = authorization
        okClient!!.interceptors().add(authorization)
    }

    fun getApiAuthorizations(): Map<String, Interceptor> {
        return apiAuthorizations
    }

    fun setApiAuthorizations(apiAuthorizations: MutableMap<String, Interceptor>) {
        this.apiAuthorizations = apiAuthorizations
    }

    fun addAuthsToOkClient(okClient: OkHttpClient) {
        for (apiAuthorization in apiAuthorizations.values) {
            okClient.interceptors().add(apiAuthorization)
        }
    }

    /**
     * Clones the okClient given in parameter, adds the auth interceptors and uses it to configure the Retrofit
     * @param okClient
     */
    fun configureFromOkclient(okClient: OkHttpClient) {
        val clone = okClient.newBuilder().build()
        addAuthsToOkClient(clone)
        adapterBuilder!!.client(clone)
    }

    init {
        apiAuthorizations = LinkedHashMap()
        createDefaultAdapter()
    }
}

/**
 * This wrapper is to take care of this case:
 * when the deserialization fails due to JsonParseException and the
 * expected type is String, then just return the body string.
 */
internal class GsonResponseBodyConverterToString<T>(
    private val gson: Gson,
    private val type: Type
) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val returned = value.string()
        return try {
            gson.fromJson(returned, type)
        } catch (e: JsonParseException) {
            returned as T
        }
    }
}

internal class GsonCustomConverterFactory private constructor(gson: Gson?) : Converter.Factory() {
    private val gson: Gson
    private val gsonConverterFactory: GsonConverterFactory
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        return if (type == String::class.java) GsonResponseBodyConverterToString<Any>(
            gson,
            type
        ) else gsonConverterFactory.responseBodyConverter(type, annotations, retrofit)
    }

    override fun requestBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        return gsonConverterFactory.requestBodyConverter(type, annotations, retrofit)
    }

    companion object {
        fun create(gson: Gson?): GsonCustomConverterFactory {
            return GsonCustomConverterFactory(gson)
        }
    }

    init {
        if (gson == null) throw NullPointerException("gson == null")
        this.gson = gson
        gsonConverterFactory = GsonConverterFactory.create(gson)
    }
}