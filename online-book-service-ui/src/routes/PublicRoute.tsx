import { type ReactElement, useContext } from "react";
import { AuthContext } from "../utils/AuthContext.tsx";
import LayoutComponent from "../components/LayoutComponent.tsx";
import { Navigate } from "react-router-dom";

function PublicRoute({ children }: {children: ReactElement}): ReactElement {
  const { token } = useContext(AuthContext);

  console.log("public")
  console.log(token);
  return !token
    ? <LayoutComponent>{children}</LayoutComponent>
    : <Navigate to={"/home"} replace />;
}

export  default PublicRoute;
