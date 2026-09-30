package com.example.pe.edu.upc.ferova_mobile_android.presentation.navigation

// Asegúrate de que estas rutas de importación coincidan con tu estructura
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pe.edu.upc.ferova_mobile_android.data.local.TokenManager
import com.example.pe.edu.upc.ferova_mobile_android.data.remote.FerovaApiClient
import com.example.pe.edu.upc.ferova_mobile_android.data.remote.api.UserApiService
import com.example.pe.edu.upc.ferova_mobile_android.data.remote.dto.LoginRequest
import com.example.pe.edu.upc.ferova_mobile_android.presentation.auth.CreateAccountScreen
import com.example.pe.edu.upc.ferova_mobile_android.presentation.auth.LoginScreen
import com.example.pe.edu.upc.ferova_mobile_android.presentation.main.MainRoutes
import com.example.pe.edu.upc.ferova_mobile_android.presentation.main.MainScreen
import com.example.pe.edu.upc.ferova_mobile_android.presentation.shared.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Auto-login con la cuenta de demo si aún no hay token guardado
    LaunchedEffect(Unit) {
        val tokenManager = TokenManager.getInstance(context)
        if (tokenManager.token == null) {
            withContext(Dispatchers.IO) {
                try {
                    val service = FerovaApiClient.create(UserApiService::class.java, context)
                    val resp = service.login(LoginRequest("12345678", "Ferova2024!"))
                    if (resp.isSuccessful) {
                        val token = resp.body()!!.token
                        tokenManager.token = token
                        // Decodificar JWT para obtener userId
                        val payloadB64 = token.split(".").getOrNull(1) ?: return@withContext
                        val decoded = String(
                            android.util.Base64.decode(payloadB64, android.util.Base64.URL_SAFE or android.util.Base64.NO_PADDING),
                            Charsets.UTF_8
                        )
                        val userId = Gson().fromJson(decoded, JsonObject::class.java)
                            ?.get("id")?.asString ?: return@withContext
                        tokenManager.userId = userId
                        // Obtener nombre del usuario
                        val userResp = service.getUserById(userId)
                        if (userResp.isSuccessful) {
                            val user = userResp.body()!!
                            tokenManager.userName     = user.name
                            tokenManager.userLastName = user.lastname
                            tokenManager.userRole     = user.role
                            tokenManager.userEmail    = user.email
                        }
                    }
                } catch (_: Exception) { /* sin red: continuar con datos mock */ }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = LoginRoute
    ) {
        composable<LoginRoute> {
            LoginScreen(
                onNavigateToCreateAccount = {
                    navController.navigate(CreateAccountRoute)
                },
                onNavigateToHome = {
                    navController.navigate(MainRoutes.MAIN) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                },
                onNavigateToRecovery = {
                    navController.navigate(RecoveryPasswordRoute)
                },
                onNavigateToAyuda = {
                    navController.navigate(AyudaRoute)
                },
                onNavigateToSeguridad = {
                    navController.navigate(SeguridadRoute)
                },
                onNavigateToPrivacidad = {
                    navController.navigate(PrivacidadRoute)
                }
            )
        }

        composable<CreateAccountRoute> {
            CreateAccountScreen(
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable<RecoveryPasswordRoute> {
            RecoveryPasswordScreen(
                onNavigateToVerification = {
                    navController.navigate(VerificationRoute)
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<VerificationRoute> {
            VerificationScreen(
                onNavigateToNewPassword = {
                    navController.navigate(NewPasswordRoute)
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<NewPasswordRoute> {
            NewPasswordScreen(
                onNavigateToLogin = {
                    navController.navigate(LoginRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(MainRoutes.MAIN) {
            MainScreen(
                onNavigateToHistory = {
                    navController.navigate(TreatmentTrackingRoute)
                },
                onLogout = {
                    navController.navigate(LoginRoute) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable<TreatmentTrackingRoute> {
            TreatmentTrackingScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<AyudaRoute> {
            AyudaScreen(onBack = { navController.popBackStack() })
        }

        composable<SeguridadRoute> {
            SeguridadScreen(onBack = { navController.popBackStack() })
        }

        composable<PrivacidadRoute> {
            PrivacidadScreen(onBack = { navController.popBackStack() })
        }
    }
}