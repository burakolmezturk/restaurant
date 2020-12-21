import { useHistory } from "react-router-dom";

const Header = () => {
    const history = useHistory();

    function logout(e) {
        e.preventDefault();
        history.push("/")


    }
    function goPage(url){
        history.push(url);
    }

    return (
        <nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a className="navbar-brand" href="#">Admin Control</a>
                <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li className="nav-item active">
                        <a className="nav-link" onClick={()=>goPage("/product")} href="#" >Products<span className="sr-only">(current)</span></a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/user")}>Users</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/category")}>Categories</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/place")} >Places</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/waiter")} >Waiter</a>
                    </li>

                    <li className="nav-item">
                        <a className="nav-link" href="#"  onClick={()=>goPage("/user")} >Reports</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/role")} >Roles</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/media")} >Media</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" href="#" onClick={()=>goPage("/info")} >Info</a>
                    </li>
                </ul>
                <form className="form-inline my-2 my-lg-0">

                    <button className="btn btn-outline-success my-2 my-sm-0" type="submit" onClick={logout}>Logout</button>
                </form>
            </div>
        </nav>
    )
}
export default Header;