import React from 'react';
import { IconBadge } from './icon-badge';

type Props = {
  icon: React.ElementType;
  title: string;
  desc: string;
};

export function Card({ icon, title, desc }: Props) {
  return (
    <div className="p-6 rounded-xl border border-gray-900 text-gray-900">
      <IconBadge icon={icon} />
      <h3 className="text-xl font-semibold mb-2 mt-4">{title}</h3>
      <p className="text-gray-900">{desc}</p>
    </div>
  );
}
