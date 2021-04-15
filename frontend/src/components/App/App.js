import './App.css';
import React, { Component } from 'react';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Header from '../Header/header';
import Books from '../Books/BookList/books';
import Categories from '../Categories/categories';
import LibraryService from '../../repository/libraryRepository';
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";


class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedProduct: {}
        }
    }

    render() {
        return(
          <Router>
              <Header />
              <main>
                  <div className="container">
                      <Route path={"/books/add"} exact render={() =>
                          <BookAdd categories={this.state.categories}
                                   authors={this.state.authors}
                                   onAddProduct={this.addBook}/>}/>
                      <Route path={"/products/edit/:id"} exact render={() =>
                          <BookEdit categories={this.state.categories}
                                    authors={this.state.authors}
                                    onEditBook={this.editBook}
                                    book={this.state.selectedProduct}/>}/>
                      <Route path={"/books/categories"} exact render={() =>
                          <Categories categories={this.state.categories} />
                      }/>
                      <Route path={"/books"} exact render={() =>
                         <Books books={this.state.books}
                                onDelete={this.deleteBook}
                                onEdit={this.getBook}
                                onMarkAsTaken={this.markBookAsTaken}
                         />
                      }/>
                      <Redirect to={"/books"}/>
                  </div>
              </main>
          </Router>
        );
    }
    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }

    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    editBook = (id, name, category, author, availableCopies) => {
        LibraryService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, author, availableCopies) => {
        LibraryService.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedProduct: data.data
                })
            })
    }

    markBookAsTaken = (id) => {
        LibraryService.markBookAsTaken(id)
            .then(() => {
                this.loadBooks();
            });
    }
}
export default App;
