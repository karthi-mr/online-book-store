import { Navigate } from "react-router-dom";
import { type ReactElement, useContext } from "react";
import LayoutComponent from "../components/LayoutComponent.tsx";
import { AuthContext } from "../utils/AuthContext.tsx";

function ProtectedRoute({ children }: {children: ReactElement}): ReactElement {
  const { token } = useContext(AuthContext);
  return token
    ? <LayoutComponent>{children}</LayoutComponent>
    : <Navigate to={"/home"} replace />;
}

export default ProtectedRoute;