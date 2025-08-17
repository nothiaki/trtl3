import {
  Database,
  Book,
  ArrowRight,
  Feather,
  MousePointerClick,
} from 'lucide-react';
import { Card } from '../components/card';
import { Footer } from '../components/footer';

export default function Home() {
  return (
    <div className="min-h-screen bg-primary-500">
      {/* Hero Section */}
      <div>
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
          <div className="flex flex-col items-center md:flex-row justify-between gap-8">
            <img src="logo.svg" alt="Trtl3's logo" className="h-48 md:h-full" />
            <div>
              <div className="flex items-end">
                <h2 className="text-2xl md:text-4xl font-semibold text-gray-900 mb-6 mr-2">
                  Meet...
                </h2>
                <h1 className="text-5xl md:text-6xl font-bold text-accent-500 mb-6">
                  Trtl3
                </h1>
              </div>
              <p className="text-l md:text-xl text-gray-900 mb-8">
                Trtl3 is a local file storage service inspired by solutions
                like Amazon S3, and other blob storage systems. Designed for
                beginner developers who want to learn how object storage works
                in practice — without the complexity of cloud services or
                external dependencies. If you're building or experimenting with
                file uploads, downloads, and basic file organization via HTTP —
                Trtl3 is a great starting point!
              </p>
              <div className="flex gap-4 flex-col md:flex-row md:items-end">
                <a
                  href="docs/"
                  className="bg-accent-500 text-primary-100 px-8 py-3 rounded-lg font-medium hover:bg-accent-600 transition-colors flex items-center gap-2 justify-center md:justify-start"
                >
                  Docs
                  <ArrowRight size={20} />
                </a>
                <span className="mb-[4px]">
                  <iframe
                    src="https://ghbtns.com/github-btn.html?user=nothiaki&amp;repo=trtl3&amp;type=star&amp;count=true&amp;size=large"
                    width={160}
                    height={30}
                    title="GitHub Stars"
                  />
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Features Section */}
      <div className="py-12">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="text-left mb-8">
            <h2 className="text-3xl font-bold text-gray-900">
              Why Choose <span className="text-accent-500">Trtl3</span>?
            </h2>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <Card
              icon={Database}
              title="Simple storage service"
              desc="Perfect for simple applications that need reliable storage without complications."
            />
            <Card
              icon={MousePointerClick}
              title="Easy to use"
              desc="Designed with simplicity in mindset up quickly, write less code, and focus on building."
            />
            <Card
              icon={Book}
              title="Great for learning"
              desc="Understand how object storage works under the hood without cloud complexity."
            />
            <Card
              icon={Feather}
              title="Lightweight"
              desc="Minimal footprint with performance optimized for speed, efficiency, and low resource usage."
            />
          </div>
        </div>
      </div>

      <Footer />
    </div>
  );
}
