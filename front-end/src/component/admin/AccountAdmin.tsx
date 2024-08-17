import { useEffect, useState } from "react";
import TableElement from "./table/TableElement";
import AccountResponse from "../../response/AccountResponse";
import PageResponse from "../../response/PageResponse";
import { readPageAccount } from "../../service/accountService";

const AccountAdminComponent = () => {
  const [accounts, setAccounts] = useState<PageResponse<AccountResponse>>();
  useEffect(() => {
    fetchReadAllAccount();
  }, []);
  const fetchReadAllAccount = () => {
    readPageAccount(1).then((t) => {
      if (t.data) setAccounts(t.data);
    });
  };
  return <TableElement array={accounts?.results??[]} />;
};

export default AccountAdminComponent;
