import { useEffect, useState } from 'react';
import ReportService from "./service/ReportService";
import {
    AreaChart, Area, XAxis, YAxis, Tooltip, CartesianGrid,
    Line, LineChart, Legend, ResponsiveContainer, PieChart, Pie, Bar,
    BarChart, Cell, RadarChart, Radar, PolarGrid, PolarAngleAxis, PolarRadiusAxis, ComposedChart,
    RadialBarChart, RadialBar, Treemap
} from 'recharts';

const Report = () => {

    const [dataCustomer, setDataCustomer] = useState([]);
    const [dataFiveProduct, setDataFiveProduct] = useState([]);
    const [dataSalesYear, setSalesYear] = useState([]);
    const [dataCategory, setDataCategory] = useState([]);
    const [dataCustomerSales, setDataCustomerSales] = useState([]);


    useEffect(() => {
        getData();


    }, []);

    const getData = async () => {

        const resFive = await ReportService.getCustomerReport();
        setDataCustomer(resFive.data);

        const res = await ReportService.getTopFiveProduct();
        setDataFiveProduct(res.data);

        const resYear = await ReportService.getSalesByYear();
        setSalesYear(resYear.data);

        const category = await ReportService.getCategoryReport();
        setDataCategory(category.data);

        const customer = await ReportService.getCustomerSales();
        setDataCustomerSales(customer.data);
    }

    const TriangleBar = (props) => {
        const {
            fill, x, y, width, height,
        } = props;

        return <path d={getPath(x, y, width, height)} stroke="none" fill={fill} />;
    };
    const getPath = (x, y, width, height) => `M${x},${y + height}
          C${x + width / 3},${y + height} ${x + width / 2},${y + height / 3} ${x + width / 2}, ${y}
          C${x + width / 2},${y + height / 3} ${x + 2 * width / 3},${y + height} ${x + width}, ${y + height}
          Z`;




    return (

        <div className="container">
            <div className="row">
                <div className="col-sm-6">
                    <h4>200$'dan Fazla Satışlar </h4>
                    <ComposedChart
                        layout="vertical"
                        width={500}
                        height={400}
                        data={dataCustomer}
                        margin={{
                            top: 20, right: 20, bottom: 20, left: 20,
                        }}
                    >
                        <CartesianGrid stroke="#f5f5f5" />
                        <XAxis type="number" />
                        <YAxis dataKey="customerName" type="category" />
                        <Tooltip />
                        <Legend />       
                        <Bar dataKey="totalPrice" barSize={20} fill="#413ea0" />
                    </ComposedChart>
                </div>
                <div className="col-sm-6">
                    <h4>Top 5 Ürünler</h4>
                    <BarChart
                        width={500}
                        height={300}
                        data={dataFiveProduct}
                        margin={{
                            top: 20, right: 30, left: 20, bottom: 5,
                        }}
                    >
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="productName" />
                        <YAxis />
                        <Bar dataKey="totalPrice" fill="#8884d8" shape={<TriangleBar />} label={{ position: 'top' }}>
                            {
                                dataFiveProduct.map((entry, index) => (
                                    <Cell key={`cell-${index}`} />
                                ))
                            }
                        </Bar>
                    </BarChart>
                </div>
            </div>
            <div className="row">
                <div className="col-sm-6">
                    <h4>Yıl Bazında Toplam Satışlar </h4>
                    <BarChart
                        width={500}
                        height={300}
                        data={dataSalesYear}
                        margin={{
                            top: 5, right: 30, left: 20, bottom: 5,
                        }}
                        barSize={20}
                    >
                        <XAxis dataKey="year" scale="point" padding={{ left: 10, right: 10 }} />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <CartesianGrid strokeDasharray="3 3" />
                        <Bar dataKey="totalPrice" fill="#1884d8" background={{ fill: '#eee' }} />
                    </BarChart>
                </div>
                <div className="col-sm-6">
                    <h4>Categori Bazlı Satışlar</h4>
                    <ComposedChart
                        width={500}
                        height={400}
                        data={dataCategory}
                        margin={{
                            top: 20, right: 20, bottom: 20, left: 20,
                        }}
                    >
                        <CartesianGrid stroke="#f5f5f5" />
                        <XAxis dataKey="categoryName" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Bar dataKey="totalPrice" barSize={20} fill="#413ea0" />
                        <Line type="monotone" dataKey="totalPrice" stroke="#ff7300" />
                    </ComposedChart>
                </div>
            </div>
            <div className="row">
                <div className="col-sm-6">
                <h4>Top 10 Müşteri</h4>
                    <RadarChart cx={300} cy={250} outerRadius={150} width={800} height={500} data={dataCustomerSales}>
                        <PolarGrid />
                        <PolarAngleAxis dataKey="customerName" />
                        <PolarRadiusAxis />
                        <Radar name="Deneme" dataKey="totalPrice" stroke="#1884d8" fill="#8884d8" fillOpacity={0.6} />
                    </RadarChart>
                </div>
            </div>


        </div>

    )

}
export default Report;