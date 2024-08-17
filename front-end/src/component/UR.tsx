import { useReducer, useRef, useState } from "react";

const up = "up";
const down = "down";
const init = 0;
const reducer = (state: number, action: string) => {
  switch (action) {
    case up:
      return state + 1;
    case down:
      return state - 1;
    default:
      throw new Error("invalid action");
  }
};
type Todo = {
  job: string;
  arrJob: string[];
};
const todo: Todo = {
  job: "",
  arrJob: [],
};
const addJob = "add";
const setJob = "set";
const removeJob = "remove";
const reducerTodo = (state: Todo, action: payload) => {
  switch (action.type) {
    case addJob:
      return { ...state, arrJob: [...state.arrJob, action.payload] };
    case setJob:
      return { ...state, job: action.payload };
    case removeJob:
    default:
      throw new Error("invalid action");
  }
};
type payload = {
  type: string;
  payload: string;
};
const SetJob = (payload: string): payload => {
  return {
    type: setJob,
    payload,
  };
};
const URComponent = () => {
    const inputRef=useRef<number>();
    const [time, setTime]=useState(60);
  const [count, dispath] = useReducer(reducer, init);
  const [todoState, dispathTodo] = useReducer(reducerTodo, todo);
  console.log(inputRef.current);
  
  const handleStart=()=>{
    inputRef.current =setInterval(()=>{
      setTime(t=>t-1);
    },1000)
  }
  const handleStop=()=>{
    clearInterval(inputRef.current);
  }
  return (
    <div>
      <div>
        <h1>{time}</h1>
        <button onClick={()=>handleStart()}>start</button>
        <button onClick={()=>handleStop()}>stop</button>
        <h1>{count}</h1>
        <button onClick={() => dispath(up)}>up</button>
        <button onClick={() => dispath(down)}>down</button>
      </div>
      <div>
        <form onSubmit={(e)=>{e.preventDefault(); dispathTodo({ type: addJob, payload: todoState.job }), dispathTodo(SetJob(""))}}>
          <input
            type="text"
            value={todoState.job}
            onChange={(e) => dispathTodo(SetJob(e.target.value))}
          />{" "}
          <button type="submit" >
            add Job
          </button>
        </form>
        <ul>
          {todoState.arrJob.map((j,index) => (
            <li key={j+index}>
              {j}
              <button
                onClick={() => dispathTodo({ type: removeJob, payload: index+"" })}
              >
                X
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default URComponent;
