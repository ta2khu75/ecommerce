import { useEffect, useState } from "react"

export const useDebounce=<T>(defaultValue: T, delay=500)=>{
    const [value, setValue]=useState<T>(defaultValue);
    useEffect(()=>{
        const timeout=setTimeout(()=>{
            setValue(defaultValue);
        },delay)
        return ()=>clearTimeout(timeout);
    },[defaultValue, delay])
    return value;
}