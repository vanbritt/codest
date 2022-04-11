export default interface Sector{
    id: number;
    name: string;
    parentSector?: Sector;
    subSectors?:Sector[];
} 