import { type FormEvent, type ReactElement, type RefObject, useRef } from "react";
import { register, type RegistrationRequest } from "../../services/AuthService.ts";
import type { AxiosError } from "axios";
import { type NavigateFunction, useNavigate } from "react-router-dom";

function RegisterPage(): ReactElement {
  const registerRef: RefObject<HTMLFormElement | null> = useRef<HTMLFormElement | null>(null);
  const navigator: NavigateFunction = useNavigate();

  function handleFormSubmit(e: FormEvent<HTMLFormElement>): void {
    e.preventDefault();
    if (!registerRef.current) {
      return;
    }
    const formData = new FormData(registerRef.current);
    console.log(formData);
    const firstname = formData.get("firstname")?.toString() || null;
    const lastname = formData.get("lastname")?.toString() || null;
    const email = formData.get("email")?.toString() || null;
    const password = formData.get("password")?.toString() || null;
    const mobile = formData.get("mobile")?.toString() || null;

    const authRequest: RegistrationRequest = {
      firstName: firstname,
      lastName: lastname,
      email: email,
      password: password,
      mobile: mobile
    };

    register(authRequest)
      .then((): void => {
        navigator("/login");
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
        <div className="w-full border-b dark:border-slate-800">
          <h1 className="text-2xl md:text-4xl text-center py-4 font-bold tracking-wide">
            Welcome to Book Store
          </h1>
        </div>
        {/* content */}
        <form
          ref={registerRef}
          onSubmit={handleFormSubmit}
          className="w-full px-4 py-2"
        >
          {/* firstname & lastname */}
          <div className="w-full flex gap-3">
            {/* firstname */}
            <div className="w-full flex flex-col gap-2 mb-3">
              <label
                className="text-sm md:text-lg tracking-wide font-medium"
                htmlFor="firstname"
              >
                Firstname
              </label>
              <input
                type="firstname"
                name="firstname"
                id="firstname"
                autoFocus
                className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600"
              />
            </div>
            {/* lastname */}
            <div className="w-full flex flex-col gap-2 mb-3">
              <label
                className="text-sm md:text-lg tracking-wide font-medium"
                htmlFor="lastname"
              >
                Lastname
              </label>
              <input
                type="text"
                name="lastname"
                id="lastname"
                autoFocus
                className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600"
              />
            </div>
          </div>
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
              autoFocus
              className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600"
            />
          </div>
          {/* mobile */}
          <div className="w-full flex flex-col gap-2 mb-3">
            <label
              className="text-sm md:text-lg tracking-wide font-medium"
              htmlFor="mobile"
            >
              Mobile number
            </label>
            <input
              type="text"
              name="mobile"
              id="mobile"
              maxLength={12}
              className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600"
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
              className="border border-gray-300 dark:border-slate-800 w-full rounded-lg focus:bg-gray-200
              focus:dark:bg-slate-900/15 p-2 text-sm md:text-lg outline-none focus:ring focus:ring-gray-500
              focus:dark:ring-slate-600"
            />
          </div>

          {/* login btn */}
          <button
            className="w-full bg-blue-800 rounded-xl py-1.5 text-sm md:text-lg font-medium tracking-wide
            text-white hover:bg-blue-700 cursor-pointer"
          >
            Register
          </button>
        </form>
        <div>

        </div>
      </div>
    </section>
  );
}

export default RegisterPage;
