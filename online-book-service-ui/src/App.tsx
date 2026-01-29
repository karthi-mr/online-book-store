import {
  type Context,
  createContext,
  type Dispatch,
  type ReactElement,
  type SetStateAction,
  useEffect,
  useState
} from "react";
import { AuthProvider } from "./utils/AuthContext.tsx";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import PublicRoute from "./routes/PublicRoute.tsx";
import LoginPage from "./pages/auth/LoginPage.tsx";
import RegisterPage from "./pages/auth/RegisterPage.tsx";
import HomePage from "./pages/HomePage.tsx";
import LayoutComponent from "./components/LayoutComponent.tsx";

type ThemeValue = {
  theme: boolean;
  toggleTheme: Dispatch<SetStateAction<boolean>>;
}

// @ts-ignore
export const ThemeContext: Context<ThemeValue> = createContext({theme: false, toggleTheme: () => {}});

function App(): ReactElement {
  const [theme, setTheme] = useState<boolean>(window.matchMedia('(prefers-color-scheme: dark)').matches);

  useEffect(() => {
    document.documentElement.classList.toggle('dark', theme);
  }, [theme])

  return (
    <ThemeContext value={{theme: theme, toggleTheme: setTheme}}>
      <AuthProvider>
        <BrowserRouter>
          <Routes>
            {/* auth pages */}
            <Route
              path="/login"
              element={<PublicRoute><LoginPage /></PublicRoute>}
            />
            <Route
              path="/register"
              element={<PublicRoute><RegisterPage /></PublicRoute>}
            />

            {/* home page */}
            <Route
              path="/home"
              element={<LayoutComponent><HomePage /></LayoutComponent>}
            />

            <Route
              path="*"
              element={<Navigate to="/home" replace />}
            />
          </Routes>
        </BrowserRouter>
      </AuthProvider>
    </ThemeContext>
  );
}

export default App;
