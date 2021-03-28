package hu.bme.communityquiz.ui.choose_category

import hu.bme.communityquiz.interactor.DataBaseInteractor
import hu.bme.communityquiz.interactor.LogicInteractor
import hu.bme.communityquiz.interactor.ServerInteractor
import hu.bme.communityquiz.network.NetworkModule
import hu.bme.communityquiz.ui.Presenter
import javax.inject.Inject

class ChooseCategoryPresenter @Inject constructor(
        private val logicInteractor: LogicInteractor,
        private val serverInteractor: ServerInteractor
) : Presenter<ChooseCategoryScreen>() {
    fun loadCategories() = serverInteractor.getCategories()
    fun setCategory(category:String) = logicInteractor.loadQuestions(category)
}