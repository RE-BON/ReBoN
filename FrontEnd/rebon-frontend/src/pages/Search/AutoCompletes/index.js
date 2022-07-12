import { Link } from 'react-router-dom';
import '../../../styles/search-auto-completes.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';

export default function AutoCompletes(props) {
  const [keyword, setKeyword] = useState([]);
  useEffect(() => {
    axios
      .get(`http://3.34.139.61:8080/api/tags/search?keyword=${props.word}`)
      .then((response) => {
        setKeyword(response.data);
        console.log(keyword);
        console.log(props.word);
      })
      .catch((error) => {
        console.log('error');
        console.log(props.word);
      });
  }, [props.word]);

  return (
    <ul className="auto-list">
      {keyword.slice(0, 3).map((item) => (
        <Link to={`/main?name=${item.name}`} state={{ item }} style={{ color: 'inherit', textDecoration: 'none' }}>
          <li key={item.name.toString()}>{item.name}</li>
        </Link>
      ))}
    </ul>
  );
}
