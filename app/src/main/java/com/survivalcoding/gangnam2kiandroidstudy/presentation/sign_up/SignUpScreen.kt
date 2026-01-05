package com.survivalcoding.gangnam2kiandroidstudy.presentation.sign_up

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.core.routing.Route
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.BigButton
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.InputField
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    backStack: NavBackStack<NavKey>,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    var isChecked by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { token ->
                viewModel.signInWithGoogle(token)
            }
        } catch (e: ApiException) {
            // 로그인 실패 처리
        }
    }

    LaunchedEffect(state.isSignUpSuccess) {
        if (state.isSignUpSuccess) {
            backStack.clear()
            backStack.add(Route.Main())
        }
    }

    Scaffold(containerColor = AppColors.white) { innerPadding ->
        Box(
            modifier = modifier
                .padding(innerPadding)
                .padding(horizontal = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Create an account",
                        style = AppTextStyles.largeTextBold,
                        color = AppColors.black
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Let’s help you set up your account,\nit won’t take long.",
                        style = AppTextStyles.smallTextRegular2,
                        lineHeight = 16.sp,
                        color = AppColors.label
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                InputField(
                    value = state.name,
                    onValueChange = viewModel::onNameChange,
                    label = "Name",
                    placeholder = "Enter Name"
                )
                Spacer(modifier = Modifier.height(20.dp))
                InputField(
                    value = state.email,
                    onValueChange = viewModel::onEmailChange,
                    label = "Email",
                    placeholder = "Enter Email"

                )
                Spacer(modifier = Modifier.height(20.dp))
                InputField(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChange,
                    label = "Password",
                    placeholder = "Enter Password"
                )
                Spacer(modifier = Modifier.height(20.dp))
                InputField(
                    value = state.confirmPassword,
                    onValueChange = viewModel::onConfirmPasswordChange,
                    label = "Confirm Password",
                    placeholder = "Retype Password"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .size(17.dp)
                            .clickable { isChecked = !isChecked }
                            .background(
                                color = if (isChecked) AppColors.secondary100 else Color.Transparent,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .border(
                                width = 1.dp,
                                color = AppColors.secondary100,
                                shape = RoundedCornerShape(5.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isChecked) {
                            Icon(
                                // 체크 아이콘
                                imageVector = Icons.Default.Check,
                                contentDescription = "Checked",
                                tint = AppColors.white,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Accept terms & Condition",
                        style = AppTextStyles.smallTextRegular2,
                        color = AppColors.secondary100,
                        modifier = Modifier.clickable { isChecked = !isChecked }
                    )
                }

                if (state.error != null) {
                    Text(
                        text = state.error!!,
                        color = Color.Red,
                        style = AppTextStyles.smallerTextRegular,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))
                BigButton(
                    text = if (state.isLoading) "Loading..." else "Sign Up"
                ) {
                    if (isChecked) {
                        viewModel.signUp()
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
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
                        onClick = {
                            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken("278798495458-fnkq372clopaerligfh3srn6e8qbrs66.apps.googleusercontent.com")
                                .requestEmail()
                                .build()
                            val googleSignInClient = GoogleSignIn.getClient(context, gso)
                            googleSignInLauncher.launch(googleSignInClient.signInIntent)
                        },
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
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Text(
                        text = "Already a member? ",
                        style = AppTextStyles.smallerTextBold,
                        color = AppColors.black
                    )
                    Text(
                        text = "Sign In",
                        style = AppTextStyles.smallerTextBold,
                        modifier = Modifier.clickable {
                            backStack.clear()
                            backStack.add(Route.SignIn)
                        },
                        color = AppColors.secondary100
                    )
                }
            }


        }
    }
}
