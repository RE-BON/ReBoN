import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { useEffect, useState } from 'react';
import { useMediaQuery } from 'react-responsive';
import Tags from './Tags';
import AutoCompletes from './AutoCompletes';
import Header from '../../components/Header';
import '../../styles/search.css';
import '../../styles/header.css';
import axios from 'axios';

export default function Search() {
  const isMobile = useMediaQuery({
    query: '(max-width:767px)',
  });
  const isTablet = useMediaQuery({
    query: '(min-width:768px) and (max-width:1199px)',
  });
  const [autoComState, setAutoComState] = useState('none');

  const onChangeState = () => {
    setAutoComState('block');
  };
  //검색창 입력받기
  const [keyword, setKeyword] = useState('');
  const [word, setWord] = useState('');
  const onChangeKeyword = (e) => {
    setWord(e.target.value);
    console.log(word);
    setKeyword(e.target.value);
    // console.log(keyword);
  };

  const [token, setToken] = useState(window.sessionStorage.getItem('token'));
  useEffect(() => {
    const config = {
      headers: { Authorization: `Bearer ${token}` },
    };
    if (token) {
      axios
        .get('http://3.34.139.61:8080/api/members', config)
        .then((response) => {
          setToken(response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }, []);
  return (
    <div className="search-background">
      <div className="search-wrapper">
        <Header />
        <div className="search-content">
          <div className="title">
            가고싶은 지역을 입력해서{isMobile ? <div></div> : null}
            <span style={{ fontFamily: 'Jua' }}> 맛집, 숙소</span>를 {isTablet ? <div></div> : null}찾아보세요!
          </div>

          <div className="input-bar">
            <input placeholder="가고싶은 지역을 입력해주세요." onChange={onChangeKeyword} onClick={onChangeState} />
            <FontAwesomeIcon icon={faSearch} className="search" />
          </div>
          <div style={{ display: autoComState }}>
            <AutoCompletes word={word} />
          </div>
          <Tags />
        </div>
      </div>
    </div>
  );
}
