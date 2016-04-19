<?php

/*
   Created with artisan
   php artisan make:model Customer
*/

namespace App;

use Illuminate\Database\Eloquent\Model;

class Customer extends Model
{
    // declare one to may relationship
    public function orders() {
        return $this->hasMany('App\Order');
    }
}
