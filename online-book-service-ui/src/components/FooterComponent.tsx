import type { ReactElement } from "react";

function FooterComponent(): ReactElement {
  return (
    <footer
      className="flex items-center justify-center py-3 border-t border-gray-300 dark:border-slate-800"
    >
      <p className="text-lg tracking-wide font-medium">&copy; All rights reserved to Karthi</p>
    </footer>
  );
}

export default FooterComponent;
