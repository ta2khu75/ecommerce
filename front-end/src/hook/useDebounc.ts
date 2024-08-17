import { useRef, useState } from "react"

export const useDebounce=<T,>(defaultValue: T, delay: number=300)=>{
    const delayRef=useRef<number>();
    const [value, _setValue]=useState<T>(defaultValue)
    const setValue=(value:T)=>{
        if(delayRef.current){
            clearTimeout(delayRef.current);
        }
        delayRef.current=setTimeout(()=>{
            _setValue(value)
        }, delay)
    }
    return [value, setValue]
}