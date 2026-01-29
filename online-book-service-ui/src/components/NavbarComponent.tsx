import { type ReactElement, useContext } from "react";
import { BookOpen, Moon, Sun } from "lucide-react";
import { ThemeContext } from "../App.tsx";
import { AuthContext } from "../utils/AuthContext.tsx";
import { type NavigateFunction, useNavigate } from "react-router-dom";

function NavbarComponent(): ReactElement {
  const { theme, toggleTheme } = useContext(ThemeContext);
  const { token, fullname, logout, setToken } = useContext(AuthContext);
  const navigator: NavigateFunction = useNavigate();

  return (
    <nav
      className="w-full h-16 px-6 md:px-10 py-2 border-b border-gray-300 dark:border-slate-800 flex items-center
      justify-between shadow-sm shadow-slate-700"
    >
      {/* logo */}
      <div className="flex gap-2 items-center justify-center">
        <BookOpen className="w-5 h-5 text-blue-700 dark:text-yellow-500" />
        <h1 className="text-2xl font-semibold tracking-wide text-shadow-xs">Book Store</h1>
      </div>

      {/* content */}
      <div className="flex items-center justify-center gap-3">
        {!token && (
          <div className="flex items-center justify-center gap-3">
            <button
              className="px-4 py-1 border border-blue-700 hover:bg-blue-700 cursor-pointer text-lg rounded-md
              font-semibold dark:text-white hover:text-white"
              onClick={() => navigator("/login")}
            >
              Login
            </button>
            <button
              className="px-4 py-1 border border-amber-700 hover:bg-amber-700 cursor-pointer text-lg
              rounded-md font-semibold text-black dark:text-white  hover:text-white"
              onClick={() => navigator("/register")}
            >
              Register
            </button>
          </div>
        )}
        {token && (
          <div className="flex items-center justify-center gap-3">
            <h2 className="text-xl font-bold tracking-wide">
              Welcome <span className="text-blue-700 dark:text-blue-400">{fullname}</span>
            </h2>
            <button
              className="px-4 py-1 bg-red-700 hover:bg-red-600 cursor-pointer text-lg
              rounded-md font-semibold text-white"
              onClick={() => {
                logout();
                setToken(null);
                navigator("/home");
              }}
            >
              Logout
            </button>
          </div>
        )}
        <button
          className="p-2 font-semibold cursor-pointer bg-gray-200 dark:bg-slate-900 rounded-full"
          onClick={() => toggleTheme(theme => !theme)}
        >
          {theme && <Sun />}
          {!theme && <Moon />}
        </button>
      </div>
    </nav>
  );
}

export default NavbarComponent;
