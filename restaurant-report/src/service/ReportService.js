import axios from 'axios';

const REPORT_API_BASE_URL = "http://localhost:8081/report/";
class ReportService{

    getTopFiveProduct(){
        return axios.get(REPORT_API_BASE_URL+"top-five-product",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getSalesByYear(){
        return axios.get(REPORT_API_BASE_URL+"sales-year",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getCustomerSales(){
        return axios.get(REPORT_API_BASE_URL+"customer-sales",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getCustomerReport(){
        return axios.get(REPORT_API_BASE_URL+"customer-report",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getCategoryReport(){
        return axios.get(REPORT_API_BASE_URL+"category-report",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
   
}
export default new ReportService()