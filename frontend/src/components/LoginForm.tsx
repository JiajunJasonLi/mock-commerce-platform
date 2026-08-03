import { useState, type SubmitEvent} from "react";
import { loginUser } from "../api/authApi.ts";

export default function LoginForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false);

    async function handleSubmit(event: SubmitEvent<HTMLFormElement>) {
        event.preventDefault();

        setError("");
        setSuccess("");
        setIsSubmitting(true);

        try {
            await loginUser({
                email, password
            });

            setSuccess("Account loginned");

        } catch(error) {
            if (error instanceof Error) {
                setError(error.message);
            } else {
                setError("Login failed");
            }
        } finally {
            setIsSubmitting(false);
        }
    }

    return (
        <form onSubmit={handleSubmit}
              className="bg-white rounded-xl shadow-lg p-8 w-full max-w-md">
            <h1 className="text-3xl font-bold text-center mb-8">User Login</h1>

            <div className="mb-5">
                <label htmlFor="email"
                       className="block text-sm font-medium mb-2">
                    Email
                </label>
                <input
                    id="email"
                    type="email"
                    value={email}
                    className="
                        w-full
                        rounded-md
                        border
                        border-gray-300
                        py-2
                        px-3
                        focus:outline-none
                        focus:ring-2
                        focus:ring-blue-500
                    "
                    onChange={(event) => setEmail(event.target.value)}
                />
            </div>

            <div className="mb-5">
                <label htmlFor="password" className="block text-sm font-medium mb-2">
                    Password
                </label>
                <input
                    id="password"
                    type="password"
                    value={password}
                    className="
                        w-full
                        rounded-md
                        border
                        border-gray-300
                        py-2
                        px-3
                        focus:outline-none
                        focus:ring-2
                        focus:ring-blue-500
                    "
                    onChange={(event) => setPassword(event.target.value)}
                />
            </div>

            {error && <p className="text-red-600 text-sm mt-2">{error}</p>}
            {success && <p className="text-green-600 text-sm mt-2">{success}</p>}

            <button type="submit"
                    className="
                        mt-6
                        w-full
                        rounded-md
                        bg-blue-600
                        py-2
                        font-semibold
                        text-white
                        hover:bg-blue-700
                        disabled:opacity-50
                        disabled:cursor-not-allowed
                    "
                    disabled={isSubmitting}>
                {isSubmitting ? "Logging in" : "Login"}
            </button>
        </form>
    );
}