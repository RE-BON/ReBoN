import '../../../styles/header-search-bar.css';
import React, { useState } from 'react';

export default function Searchbar() {
  const [word, setWord] = useState('');
  const onChangeWord = (e) => {
    setWord(e.target.value);
    console.log(word);
  };
  return (
    <div className="search-bar-wrapper">
      <input placeholder="가고 싶은 지역을 입력해주세요." value={word} onChange={onChangeWord} />
      <img src="../../../../image/search-icon.png" alt="header-search-icon" />
    </div>
  );
}
