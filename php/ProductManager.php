<?php 

class ProductManager 
{
    /**
     * @var PDO
     */
    private $db;

    /**
     * ProductManager constructor.
     * @param PDO $db
     */
    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function getAll($db) {
        $results = $db->query("SELECT * FROM product")->fetchAll();
        header("content-type", "application/json");
        $products = [];
        foreach($results as $row) {
            $products[] = $row;
        }
        return json_encode($products);
    }

    public function getAllOrderByCategory($db) {
        $results = $db->query("SELECT * FROM product ORDER BY idCat")->fetchAll();
        header("content-type", "application/json");
        $products = [];
        foreach($results as $row) {
            $products[] = $row;
        }
        return json_encode($products);
    }

    public function getAllOrderByStore($db) {
        $results = $db->query("SELECT * FROM product ORDER BY idStore")->fetchAll();
        header("content-type", "application/json");
        $products = [];
        foreach($results as $row) {
            $products[] = $row;
        }
        return json_encode($products);
    }

    public function getAllByCategory($db, $id) {
        $results = $db->query("SELECT * FROM product WHERE idCat = " . $id)->fetchAll();
        header("content-type", "application/json");
        $products = [];
        foreach($results as $row) {
            $products[] = $row;
        }
        return json_encode($products);
    }

    public function getAllByStore($db, $id) {
        $results = $db->query("SELECT * FROM product WHERE idStore = " . $id)->fetchAll();
        header("content-type", "application/json");
        $products = [];
        foreach($results as $row) {
            $products[] = $row;
        }
        return json_encode($products);
    }

    public function getAllByCategoryAndStore($db, $idCat, $idStore) {
        $results = $db->query("SELECT * FROM product WHERE idCat = " . $idCat . " AND idStore = " . $idStore)->fetchAll();
        header("content-type", "application/json");
        $products = [];
        foreach($results as $row) {
            $products[] = $row;
        }
        return json_encode($products);
    }

    public function findById($db, $id) {
        $result = $db->query("SELECT * FROM product WHERE id = " . $id)->fetch();
        header("content-type", "application/json");
        return json_encode($result);
    }

    public function create($db, $product) {
        $name = $product->name;
        $brand = $product->brand;
        $price = (double) $product->price;
        $idCat = (int) $product->idCat;
        $idStore = (int) $product->idStore;
        $result = $db->prepare("INSERT INTO product (name, brand, price, idCat, idStore) VALUES (:name, :brand, :price, :idCat, :idStore)")
            ->execute([':name' => $name, ':brand' => $brand, ':price' => $price, ':idCat' => $idCat, ':idStore' => $idStore]);
        return json_encode($result);
    }

    public function update($db, $product) {
        $id = (int) $product->id;
        $name = $product->name;
        $brand = $product->brand;
        $price = (double) $product->price;
        $idCat = (int) $product->idCat;
        $idStore = (int) $product->idStore;
        $result = $db->prepare("UPDATE product SET name = :name, brand = :brand, price = :price, idCat = :idCat, idStore = :idStore WHERE id = :id")
            ->execute([':name' => $name, ':brand' => $brand, ':price' => $price, ':idCat' => $idCat, ':idStore' => $idStore, ':id' => $id]);
        return json_encode($result);
    }

    public function delete($db, $id) {
        $result = $db->prepare("DELETE FROM product WHERE id = :id")->execute([':id' => $id]);
        return json_encode($result);
    }

}
