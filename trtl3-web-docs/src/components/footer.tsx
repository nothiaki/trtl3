export function Footer() {
  return (
    <footer className="py-12">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center">
          <div className="text-gray-900">
            Copyright &copy; 2025 - Present Trlt3.
          </div>
          <div className="flex gap-6">
            <a href="docs/" className="text-gray-900 hover:text-accent-500">
              Docs
            </a>
            <a
              href="https://github.com/nothiaki/trtl3"
              target="_blank"
              className="text-gray-900 hover:text-accent-500"
            >
              GitHub
            </a>
          </div>
        </div>
      </div>
    </footer>
  );
}
