import LoginForm from "../components/LoginForm";
import {Link} from "react-router";

export default function LoginPage() {
    return (
        <main className="min-h-screen bg-gray-100 flex items-center justify-center">
            <LoginForm />

            <p className="mt-4">
                Don't have an account?
                <Link to="/register"
                      className="text-blue-600 hover:underline">
                    Register
                </Link>
            </p>
        </main>
    );
}