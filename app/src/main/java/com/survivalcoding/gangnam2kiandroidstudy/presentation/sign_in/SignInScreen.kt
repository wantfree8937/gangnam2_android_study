package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_in

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey>
) {
    Scaffold(containerColor = AppColors.white) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Hello,",
                        style = AppTextStyles.headerTextBold,
                        color = AppColors.black
                    )
                    Text(
                        text = "Welcome Back!",
                        style = AppTextStyles.largeTextBold,
                        color = AppColors.label
                    )
                }
                Spacer(modifier = Modifier.height(57.dp))
                InputField(
                    value = "",
                    onValueChange = {},
                    label = "Email",
                    placeholder = "Enter email"
                )
                Spacer(modifier = Modifier.height(30.dp))
                InputField(
                    value = "",
                    onValueChange = {},
                    label = "Enter Password",
                    placeholder = "Enter password"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Forgot Password?",
                        style = AppTextStyles.smallTextRegular2,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .clickable { },
                        color = AppColors.secondary100
                    )
                }
                Spacer(modifier = Modifier.height(25.dp))
                BigButton(
                    text = "Sign In"
                ) {
                    backStack.clear()
                    backStack.add(Route.Main)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 가로선
                    HorizontalDivider(
                        modifier = Modifier.width(50.dp),
                        color = AppColors.gray4
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = "Or Sign in With",
                        style = AppTextStyles.smallerTextBold,
                        color = AppColors.gray4
                    )
                    Spacer(modifier = Modifier.width(7.dp))
                    HorizontalDivider(
                        modifier = Modifier.width(50.dp),
                        color = AppColors.gray4
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    ElevatedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(44.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = AppColors.white
                        ),
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Google Sign In",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(25.dp))
                    ElevatedButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(44.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.elevatedButtonColors(
                            containerColor = AppColors.white
                        ),
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook),
                            contentDescription = "Facebook Sign In",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(55.dp))
                Row {
                    Text(
                        text = "Don't have an account? ",
                        style = AppTextStyles.smallerTextBold,
                        color = AppColors.black
                    )
                    Text(
                        text = "Sign Up",
                        style = AppTextStyles.smallerTextBold,
                        modifier = Modifier.clickable {
                            backStack.clear()
                            backStack.add(Route.SignUp)
                        },
                        color = AppColors.secondary100
                    )
                }
            }
        }
    }
}