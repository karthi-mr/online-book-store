import { type FormEvent, type ReactElement, type RefObject, useContext, useRef } from "react";
import { authenticate, type AuthenticationRequest, type AuthenticationResponse } from "../../services/AuthService.ts";
import type { AxiosError, AxiosResponse } from "axios";
import { AuthContext } from "../../utils/AuthContext.tsx";
import { type NavigateFunction, useNavigate } from "react-router-dom";

function LoginPage(): ReactElement {
  const loginRef: RefObject<HTMLFormElement | null> = useRef<HTMLFormElement | null>(null);
  const { login } = useContext(AuthContext);
  const navigator: NavigateFunction = useNavigate();

  function handleFormSubmit(e: FormEvent<HTMLFormElement>): void {
    e.preventDefault();
    if (!loginRef.current) {
      return;
    }
    const formData = new FormData(loginRef.current);
    console.log(formData);
    const email = formData.get("email")?.toString() || null;
    const password = formData.get("password")?.toString() || null;

    const authRequest: AuthenticationRequest = {
      email: email,
      password: password
    };

    authenticate(authRequest)
      .then((response: AxiosResponse<AuthenticationResponse>): void => {
        login(response.data.token);
        navigator("/home");
      })
      .catch((err: AxiosError) => {
        console.log(err);
      })
  }

  return (
    <section className="w-full flex items-center justify-center">
      <div className="flex items-center justify-center min-w-lg md:min-w-xl flex-col gap-2 border
      border-gray-300 dark:border-slate-800 rounded-xl">
        {/* header */}
        <div className="w-full border-b border-gray-300 dark:border-slate-800">
          <h1 className="text-2xl md:text-4xl text-center py-4 font-bold tracking-wide">
            Welcome to Book Store
          </h1>
        </div>
        {/* content */}
        <form
          ref={loginRef}
          onSubmit={handleFormSubmit}
          className="w-full px-4 py-2"
        >
          {/* email */}
          <div className="w-full flex flex-col gap-2 mb-3">
            <label
              className="text-sm md:text-lg tracking-wide font-medium"
              htmlFor="email"
            >
              Email
            </label>
            <input
              type="email"
              name="email"
              id="email"
              placeholder="Enter your username"
              autoFocus
              className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600 placeholder:text-gray-400 dark:placeholder:text-slate-600"
            />
          </div>
          {/* password */}
          <div className="w-full flex flex-col gap-2 mb-3">
            <label
              className="text-sm md:text-lg tracking-wide font-medium"
              htmlFor="password"
            >
              Password
            </label>
            <input
              type="password"
              name="password"
              id="password"
              placeholder="Enter your password"
              className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600 placeholder:text-gray-400 dark:placeholder:text-slate-600"
            />
          </div>

          {/* login btn */}
          <button
            className="w-full border border-blue-700 rounded-xl py-1.5 text-sm md:text-lg font-medium tracking-wide
            hover:text-white hover:bg-blue-700 cursor-pointer transition-colors duration-300"
          >
            Login
          </button>
        </form>
        <div>

        </div>
      </div>
    </section>
  );
}

export default LoginPage;
