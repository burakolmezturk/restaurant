import Loader from 'react-loader-spinner';

const Loading=()=>{

    return(
        <div style={{
            width: '100%',
            height: "100",
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            marginTop: '20%'
        }}>
            <Loader type="ThreeDots" color="#323e4f" height="100" width="150" />
        </div>
    )

}
export default Loading;