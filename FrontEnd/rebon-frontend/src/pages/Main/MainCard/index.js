import React,{useState} from 'react';
import '../../../styles/main.css';
import { FiHeart } from 'react-icons/fi';
import { FaHeart } from 'react-icons/fa';

export default function MainCard() {
  const [like,setLike] = useState(false);

  const likeClick =()=>{
    if(like){
      setLike(false);
    }else{
      setLike(true);
    }
  }

  const [mainInfo,setMainInfo] = useState([
    {
      "id":4,
      "name":"인브리즈",
      "star":0.0,
      "like":false,
      "tags":[{
        "id":1,
        "name":"포항"
      },{
        "id":3,
        "name":"한동대"
      }],
      "image" : "https://mblogthumb-phinf.pstatic.net/MjAxODA3MDNfMjAy/MDAxNTMwNjI5NjE1MTk1.cJeJTxszRfHGMw3q3hqSN4LLBS5scVV4lUBWsjEYLgcg.b1MlKG__mr8dVbZq7KGaFVZ3uttt9Ya_RBAJTgR8F9wg.JPEG.netusu/IMG_4822.jpg?type=w800"
    },{
      "id":5,
      "name":"미스터 피자",
      "star":0.0,
      "like":false,
      "tags":[
        {
          "id" : 1,
          "name" : "포항"
        },{
          "id":4,
          "name":"양덕"
        }
      ],
      "image" : "https://cdn.tleaves.co.kr/news/photo/202202/1991_2819_1810.jpg"
    }
  ])

  return (
    <>
    {mainInfo.map((item,idx) => (
    <div className="mainCard">
      <div className='mainCard-img'>
      <img class="main-img"
      src={item.image}/>
      </div>
      <div className='likeBtn-main'>
        {like?
        <FaHeart className="heart-icon" md={8} size="22" onClick={likeClick}/>:
        <FiHeart className="heart-icon-fi" md={8} size="22" onClick={likeClick}/>
        }
      </div>
      <div className='mainCard-bottom'>
        <div className="titleRow">
          <div className="placeName-main">{item.name}</div>
          <div className="starNum">4.9</div>
        </div>
        <div className="">
          <span className="tag">칠포해수욕장</span>
          <span className="tag">칠포읍</span>
          <span className="tag">한식</span>
        </div>
      </div>
    </div>
    ))}
    </>

  );
}
