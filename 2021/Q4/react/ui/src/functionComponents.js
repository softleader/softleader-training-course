export function CreditInput(props) {
  let splited = props.value.split("-");
  return (
    <>
      <input style={{width: "3em"}} value={splited.length > 0 && splited[0] || ""}/>-
      <input style={{width: "3em"}} value={splited.length > 1 && splited[1] || ""}/>-
      <input style={{width: "3em"}} value={splited.length > 2 && splited[2] || ""}/>-
      <input style={{width: "3em"}} value={splited.length > 3 && splited[3] || ""}/>
    </>
  )
}