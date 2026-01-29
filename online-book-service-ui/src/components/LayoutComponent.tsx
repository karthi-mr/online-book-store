import type { ReactElement } from "react";
import NavbarComponent from "./NavbarComponent.tsx";
import FooterComponent from "./FooterComponent.tsx";

type LayoutProps = {
  children: ReactElement;
}

function LayoutComponent({ children }: LayoutProps): ReactElement {

  return (
    <div className="min-h-screen flex flex-col">
      <NavbarComponent />
      <main className="grow flex">
        {children}
      </main>
      <FooterComponent />
    </div>
  );
}

export default LayoutComponent;
