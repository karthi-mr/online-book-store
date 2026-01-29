import * as React from "react";
import { createContext, type ReactElement, useState } from "react";
import { jwtDecode } from "jwt-decode";

type AuthProviderProps = {
  children: ReactElement;
}

export type AuthProviderValue = {
  token: string | null;
  login: (jwtToken: string) => void;
  logout: () => void;
  fullname: string;
  roles: string[];
  setToken?: any;
}

type MyTokenAuthority = {
  authority: string;
}

export type MyTokenPayload = {
  fullname: string;
  sub: string;
  iat: number;
  exp: number;
  authorities: MyTokenAuthority[];
}

export const AuthContext: React.Context<AuthProviderValue> = createContext<AuthProviderValue>({
  token: null,
  login: () => {},
  logout: () => {},
  fullname: "",
  roles: []
});


export function AuthProvider({ children }: AuthProviderProps): ReactElement {
  const [token, setToken] = useState<string | null>(localStorage.getItem("token"));

  console.log(`Token: ${token}`);
  let fullname: string = "";
  let roles: string[] = [];
  let exp: number = 0;

  if (token !== null) {
    try {
      const decoded = jwtDecode<MyTokenPayload>(token);
      console.log(decoded);
      fullname = decoded.fullname;
      exp = decoded.exp;
      roles = decoded.authorities.map(role => role.authority);
    } catch (error) {
      console.log("Invalid token:", error);
    }
  }

  const login = (jwtToken: string): void => {
    localStorage.setItem("token", jwtToken);
    setToken(jwtToken);
  }

  const logout = (): void => {
    localStorage.removeItem("token");
  }

  if ((exp * 1000) - Date.now() <= 0) {
    logout();
  }

  return (
    <AuthContext value={{ token, login, logout, fullname, roles, setToken }}>
      {children}
    </AuthContext>
  )
}
