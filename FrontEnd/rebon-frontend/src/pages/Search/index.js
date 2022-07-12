import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import { useState } from 'react';

import Tags from './Tags';
import AutoCompletes from './AutoCompletes';
import Header from '../../components/Header';
import '../../styles/search.css';

export default function Search() {
  const [autoComState, setAutoComState] = useState('none');
  const [keyword, setKeyword] = useState();

  const onChangeState = () => {
    setAutoComState('block');
  };
  //검색창 입력받기
  const [word, setWord] = useState('');
  const onChangeKeyword = (e) => {
    setWord(e.target.value);
    console.log(word);
    setKeyword(e.target.value);
    // console.log(keyword);
  };

  return (
    <div className="search-background">
      <div className="search-wrapper">
        <Header />
        <div className="search-content">
          <div className="title">
            가고싶은 지역을 입력해서
            <span style={{ fontFamily: 'Jua' }}> 맛집, 숙소</span>를 찾아보세요!
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
