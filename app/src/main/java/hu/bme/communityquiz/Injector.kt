package hu.bme.communityquiz

import android.app.Activity
import androidx.fragment.app.Fragment


val Activity.injector: CommunityQuizApplicationComponent
    get() {
        return (this.applicationContext as CommunityQuizApplication).injector
    }

