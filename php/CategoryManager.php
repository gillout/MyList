<?php

class CategoryManager 
{
    /**
     * @var PDO
     */
    private $db;

    /**
     * CategoryManager constructor.
     * @param PDO $db
     */
    public function __construct(PDO $db)
    {
        $this->db = $db;
    }

    public function getAll($db) {
        $results = $db->query("SELECT * FROM category")->fetchAll();
        header("content-type", "application/json");
        $categories = [];
        foreach($results as $row) {
            $categories[] = $row;
        }
        return json_encode($categories);
    }

    public function findById($db, $id) {
        $result = $db->query("SELECT * FROM category WHERE id = " . $id)->fetch();
        header("content-type", "application/json");
        return json_encode($result);
    }

    public function create($db, $category) {
        $name = $category->name;
        $result = $db->prepare("INSERT INTO category (name) VALUES (:name)")
            ->execute([':name' => $name]);
        return json_encode($result);
    }

    public function update($db, $category) {
        $id = (int) $category->id;
        $name = $category->name;
        $result = $db->prepare("UPDATE category SET name = :name WHERE id = :id")
            ->execute([':name' => $name, ':id' => $id]);
        return json_encode($result);
    }

    public function delete($db, $id) {
        $result = $db->prepare("DELETE FROM category WHERE id = :id")->execute([':id' => $id]);
        return json_encode($result);
    }

}
