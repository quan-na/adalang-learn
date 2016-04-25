<?php

/*
   Created with artisan
   php artisan make:controller CustomerController
*/

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Http\Requests;

use App\Customer as Customer;

class CustomerController extends Controller
{
    public function customer($id) {
        $customer = Customer::find($id);
        //echo '<pre>';
        //print_r($customer);
        //echo '</pre>';
        //echo $customer->name . '<br/>';
        //echo "Orders : <br/> <ul>";
        //foreach ($customer->orders as $order) {
            //echo "<li>". $order->name . "</li>";
        //}
        //echo "</ul>";
        return view('customer', array('customer' => $customer));
    }
}
