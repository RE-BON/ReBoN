import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import Tags from './Tags';
import axios from 'axios';
import Header from '../../components/Header';
import '../../styles/search.css';
export default function Search() {
  //검색창 입력받기
  const [word, setWord] = useState('');
  const onChangeKeyword = (e) => {
    setWord(e.target.value);
    console.log(word);
  };

  const [keyword, setKeyword] = useState([]);
  useEffect(() => {
    axios
      .get(`http://3.34.139.61:8080/api/tags/search?keyword=${word}`)
      .then((response) => {
        setKeyword(response.data);
        console.log(keyword);
      })
      .catch((error) => {
        console.log('error');
      });
  }, [word]);
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
            {keyword.slice(0, 3).map((item) => (
              <Link to={`/main?name=${item.name}`} state={{ item }}>
                <li>{item.name}</li>
              </Link>
            ))}
            <Link to={`/main?name=${word}`} state={{}} style={{ color: 'inherit', textDecoration: 'none' }}>
              <FontAwesomeIcon icon={faSearch} className="search" />
            </Link>
          </div>

          <Tags />
        </div>
      </div>
    </div>
  );
}
