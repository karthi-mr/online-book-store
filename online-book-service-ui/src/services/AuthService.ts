import axios, { type AxiosResponse } from "axios";
import { AUTHENTICATION_ENDPOINT } from "../utils/endpoints.ts";


export type AuthenticationRequest = {
  email: string | null;
  password: string | null;
}

export type AuthenticationResponse = {
  token: string;
}

export type RegistrationRequest = {
  firstName: string | null;
  lastName: string | null;
  email: string | null;
  password: string | null;
  mobile: string | null;
}

export function authenticate(
  authRequest: AuthenticationRequest
): Promise<AxiosResponse<AuthenticationResponse, unknown>> {
  return axios.post(`${AUTHENTICATION_ENDPOINT}/login`, authRequest);
}

export function register(
  authRequest: RegistrationRequest
): Promise<AxiosResponse<unknown, unknown>> {
  return axios.post(`${AUTHENTICATION_ENDPOINT}/register`, authRequest);
}
