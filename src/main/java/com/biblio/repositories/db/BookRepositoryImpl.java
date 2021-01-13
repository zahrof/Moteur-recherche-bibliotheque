package com.biblio.repositories.db;

import com.biblio.models.Book;
import org.apache.lucene.queries.function.FunctionMatchQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Bindable;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class BookRepositoryImpl implements BookRepositoryCustom<Book, String>{

    @Autowired
    private EntityManager entityManager;
    .;

    @Override
    public List<Book> findByExpression(String keyword) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = builder.createQuery(Book.class);
        Root<Book> pageField = query.from(Book.class);
        query.select(pageField).where(kmp(pageField.get("title"), keyword));
    }


    private Expression kmp(Path<?> title, String keyword){
        Expression expr = title.get("title");


        return null;
    }
}

/*
@Override
    public List<AuthorSummaryDTO> getAuthorsByFirstName(String firstName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AuthorSummaryDTO> query = cb.createQuery(AuthorSummaryDTO.class);
        Root<Author> root = query.from(Author.class);
        query.select(
                cb.construct(AuthorSummaryDTO.class, root.get(Author_.firstName), root.get(Author_.lastName))
            ).where(
                cb.equal(root.get(Author_.firstName), firstName)
            );

        return entityManager.createQuery(query).getResultList();
    }
 */
