import { useEffect, useState } from 'react'
import './App.css'
import ProductList from './assets/ProductList';
import CategoryFilter from './CategoryFilter';

function App() {
  // create a state for product and category
  const [products, setProducts] = useState([]);
  const [categories, setCategories] = useState([]);

  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [sortOrder, setSortOrder] = useState("asc");

  // useeffect is to get item
  useEffect(() => {
    // fetch product
    fetch('http://localhost:8080/api/product')
    .then(response => response.json())
    .then(data => setProducts(data));

    // fetch category
    fetch('http://localhost:8080/api/category')
    .then(response => response.json())
    .then(data => setCategories(data));
  }, []);

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  }


  const handleSortChange = (event) => {
    setSortOrder(EventTarget.target.value)
  }

  const handleCategorySelect = (categoryId) => {
    setSelectedCategory(categoryId ? Number(categoryId) : null)
  }

  // function to filter product
  const filterProduct = products.filter(product => {
    return ((selectedCategory ? product.category.id === selectedCategory: true) &&
    product.name.toLowerCase().includes(searchTerm.toLowerCase()));
  })
  .sort((a,b) => {
    if (sortOrder === "asc") {
      return a.price - b.price
    } else {
      return b.price - a.price
    }
  });

  return (
    <div className='container'>
      <h1 className='my-4'>Takeo Local Products</h1>

      <div className='row align-items-center mb-4'>

        {/* div for filter */}
        <div className='col-md3 col-sm-2 mb-2'>
          <p><CategoryFilter categories={categories} onSelect={handleCategorySelect}></CategoryFilter></p>
        </div>

        {/* div for searching */}
        <div className='col-md-5 col-sm-12 mb-2'>
          <input type="text" className='form-control' placeholder='Search Product' onChange={handleSearchChange}/>
        </div>

        {/* div for category asc and dsc */}
        <div className='col-md-4 col-sm-2 mb-2'>
          <select className='form-control' onChange={handleSortChange}>
            <option value="asc">Sort By Price: Low to High</option>
            <option value="dsc">Sort By Price: High to Low</option>
          </select>
        </div>

      </div>
      <div>
        {products.length ? (
          // display product
          <ProductList products={filterProduct}></ProductList>
          ) : (
            <p>No products found</p>
          )}
      </div>
      
    </div>
    
  )
}

export default App
