package hu.bme.communityquiz

import dagger.Component
import hu.bme.communityquiz.interactor.InteractorModule
import hu.bme.communityquiz.mock.MockNetworkModule2
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule2::class, AndroidTestModule::class, InteractorModule::class])
interface AndroidTestComponent : CommunityQuizApplicationComponent
