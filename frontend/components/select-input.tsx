import { UseFormRegisterReturn } from "react-hook-form";

interface SelectOption {
  id: string;
  value: string;
}

interface SelectInputProps {
  id: string;
  label: string;
  placeholder?: string;
  required?: boolean;
  register?: UseFormRegisterReturn;
  error?: string;
  options: SelectOption[];
}

export default function SelectInput({
  id,
  label,
  placeholder,
  required = false,
  register,
  error,
  options,
}: SelectInputProps) {
  return (
    <div className="w-full">
      <label
        htmlFor={id}
        className="block text-sm font-medium text-gray-700 pb-2"
      >
        {label}
      </label>
      <select
        id={id}
        required={required}
        {...register}
        className="mt-1 block w-full border-b border-b-gray-300 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm outline-none pb-2 text-black bg-transparent"
      >
        {placeholder && (
          <option value="" disabled selected>
            {placeholder}
          </option>
        )}
        {options.map((option) => (
          <option key={option.id} value={option.id}>
            {option.value}
          </option>
        ))}
      </select>
      {error && <p className="text-red-500 text-sm mt-1">{error}</p>}
    </div>
  );
}