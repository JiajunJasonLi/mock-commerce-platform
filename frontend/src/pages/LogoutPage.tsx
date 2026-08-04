import { useState } from "react";
import { userLogout } from "../api/authApi.ts";
import {useNavigate} from "react-router";

export default function LogoutPage() {
    const navigate = useNavigate();

    const [isLoggingOut, setIsLoggingOut] = useState(false);
    const [error, setError] = useState("");

    async function handleLogout() {
        try {
            setIsLoggingOut(true);
            setError("");

            await userLogout();

            navigate("/login");
        } catch (error) {
            if (error instanceof Error) {
                setError(error.message);
            } else {
                setError("Logout failed");
            }
        } finally {
            setIsLoggingOut(false);
        }
    }

    return (
        <main className="min-h-screen bg-gray-100 flex flex-col items-center justify-center">
            <button
                type="button"
                onClick={handleLogout}
                disabled={isLoggingOut}
                className="rounded-md bg-red-600 px-4 py-2 text-white disabled:cursor-not-allowed disabled:opacity-60">Logout
            </button>


            {error && (
                <p className="mt-4 text-red-600">
                    {error}
                </p>
            )}
        </main>
    );
}