import { userInfoApi, userLogoutApi } from "../../apis/userApi";
import { useState, useEffect } from "react";
import userImg from "../../assets/userImg.svg";
import { Link, useNavigate } from "react-router-dom";

function NewModal() {
  const navigate = useNavigate();
  const [info, setInfo] = useState({
    user: {
      userId: '',
      userName: '',
      userEmail: '',
      userMileage: '',
      userAddress: '',
    },
    orders: [],
  });

  const token = localStorage.getItem("token");

  const userInfo = async (token) => {
    try {
      const res = await userInfoApi(token);
      console.log(res);
      setInfo({
        user: res.data.userMyPageDto,
        orders: res.data.orders,
      });
    } catch (error) {
      console.log(error);
    }
  };

  const Logout = async () => {
    try {
      await userLogoutApi(token);
      navigate("/");
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    userInfo(token);
  }, [token]);

  return (
      <div className="myPageModalFirstClass">
        <div className="ModalBorder">
          <div className="modalTop">ITunes.exe - 응용 프로그램 오류</div>
          <div className="myPageBox">
            <Link to="/home" style={{ textDecoration: "none", color: "black" }}>
              <button className="toHome">X</button>
            </Link>
            <div className="userBox">
              <img src={userImg} alt="user" />
              <div className="userInfo">
                <div className="userName">{info.user.userName}</div>
                <div className="userRegion">{info.user.userAddress}</div>
                <div className="userMileage">
                  현재 마일리지 : {info.user.userMileage}P
                </div>
                <div className="userTime">잔여시간 : 00:00</div>
                <button onClick={Logout} className="logout">
                  로그아웃
                </button>
              </div>
            </div>
            <div className="purchaseLine">
              -------------- 구매한 기프티콘 ---------------
            </div>

            <div className="giftList">
              {info.orders.map((order) => {
                return (
                    <Link
                        to={`/order/${order.id}`} // Adjust link as necessary
                        key={order.id}
                        style={{ textDecoration: "none", color: "black" }}
                    >
                      <div className="product">
                        <img src={order.image} alt="order" />
                        <div className="name">{order.name}</div>
                      </div>
                    </Link>
                );
              })}
            </div>
          </div>
          <div className="OKbutton">
            <Link to="/home" style={{ textDecoration: "none", color: "black" }}>
              <button>확인</button>
            </Link>
          </div>
        </div>
      </div>
  );
}
export default NewModal;
