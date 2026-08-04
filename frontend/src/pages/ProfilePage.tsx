import {useEffect, useState} from "react";
import type { ProfileResponse } from "../types/auth.ts";
import { userProfile } from "../api/authApi.ts";

export default function RegisterPage() {
    const [profile, setProfile] = useState<ProfileResponse | null>(null);

    useEffect(() => {
        async function loadProfile() {
            const response = await userProfile();
            setProfile(response)
        }

        loadProfile();
    }, []);


    if (profile === null) {
        return <div>Loading...</div>;
    }


    return (
        <main className="min-h-screen bg-gray-100 flex flex-col items-center justify-center">
            Profile page
            <div>
                <p>Username: {profile.username}</p> <br/>
                <p>Email: {profile.email}</p> <br/>
                <p>Tier: {profile.membershipTier}</p> <br/>
            </div>

        </main>
    );
}