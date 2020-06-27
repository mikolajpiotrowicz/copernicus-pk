export type Nullable<T> = T | null | undefined;

export type PaginateSpring<EntityList> = {
  content: EntityList[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  size: number;
  sort: {
    empty: boolean;
    sorted: boolean;
    unsorted: boolean;
  };
  pageable: {
    pageNumber: number;
  }
  totalElements: number;
  totalPages: number;
};
