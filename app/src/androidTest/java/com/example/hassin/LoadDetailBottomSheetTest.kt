package com.example.hassin

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.hassin.domain.model.Load
import com.example.hassin.presentation.ui.screens.LoadDetailBottomSheet
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class)
class LoadDetailBottomSheetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val sampleLoad = Load(
        id = 1,
        originCity = "تهران",
        originProvince = "تهران",
        destinationCity = "کرمان",
        destinationProvince = "کرمان",
        weightInTon = 12,
        priceInMillionToman = 2.5,
        packaging = "کونی",
        loadingDate = "۱۴۰۳/۰۳/۱۰",
        itemName = "سیمان"
    )

    @Test
    fun testLoadDetailBottomSheet_showsAllTextsAndButtons() {
        composeTestRule.setContent {
            LoadDetailBottomSheet(
                load = sampleLoad,
                onConfirm = {},
                onDismiss = {}
            )
        }

        // بررسی نمایش متن‌ها
        composeTestRule.onNodeWithText("جزئیات بار").assertIsDisplayed()
        composeTestRule.onNodeWithText("مبدا:").assertIsDisplayed()
        composeTestRule.onNodeWithText("کرمان").assertIsDisplayed()
        composeTestRule.onNodeWithText("بسته‌بندی:").assertIsDisplayed()
        composeTestRule.onNodeWithText("سیمان").assertIsDisplayed()

        // بررسی دکمه تأیید
        composeTestRule.onNodeWithText("با 2.5 میلیون تومان کرایه می‌برم").assertIsDisplayed()
    }

    @Test
    fun testConfirmButtonCallsOnConfirm() {
        var confirmed = false

        composeTestRule.setContent {
            LoadDetailBottomSheet(
                load = sampleLoad,
                onConfirm = { confirmed = true },
                onDismiss = {}
            )
        }

        composeTestRule.onNodeWithText("با 2.5 میلیون تومان کرایه می‌برم")
            .performClick()

        assert(confirmed)
    }

    @Test
    fun testCloseIconCallsOnDismiss() {
        var dismissed = false

        composeTestRule.setContent {
            LoadDetailBottomSheet(
                load = sampleLoad,
                onConfirm = {},
                onDismiss = { dismissed = true }
            )
        }

        composeTestRule.onNodeWithContentDescription("بستن")
            .performClick()

        assert(dismissed)
    }


}
