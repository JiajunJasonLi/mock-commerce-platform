export interface RegistrationRequest {
    username: string,
    email: string,
    password: string;
}

export interface ApiError {
    code: string,
    message: string;
}