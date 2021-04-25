package hu.bme.communityquiz

import dagger.Component
import hu.bme.communityquiz.mock.MockNetworkModule
import hu.bme.communityquiz.test.MainTest
import hu.bme.communityquiz.interactor.InteractorModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class])
interface TestComponent : CommunityQuizApplicationComponent {
    fun inject(mainTest: MainTest)
}
