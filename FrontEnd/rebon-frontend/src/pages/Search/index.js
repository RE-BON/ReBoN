import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';
import { Link } from 'react-router-dom';
import Tags from './Tags';
import Header from '../../components/Header';
import '../../styles/search.css';
export default function Search() {
  //검색창 입력받기
  const [keyword, setKeyword] = useState('');
  const onChangeKeyword = (e) => {
    setKeyword(e.target.value);
    console.log(keyword);
  };
  return (
    <div className="search-background">
      <div className="search-wrapper">
        <Header />
        <div className="search-content">
          <div className="title">
            가고 싶은 지역을 입력해서 <br />
            <span style={{ fontFamily: 'Jua' }}>맛집, 숙소</span>를 찾아보세요!
          </div>

          <div className="input-bar">
            <input placeholder="가고싶은 지역을 입력해주세요." onChange={onChangeKeyword} />
            <Link to={`/main?name=${keyword}`} state={{}} style={{ color: 'inherit', textDecoration: 'none' }}>
              <FontAwesomeIcon icon={faSearch} className="search" />
            </Link>
          </div>

          <Tags />
        </div>
      </div>
    </div>
  );
}
