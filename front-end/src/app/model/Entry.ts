import Sector from "./Sector";

export default interface Entry{
    id:number;
    name: string;
    sectors: Sector[];
    agreeToTerms: boolean;
}